QuizPlayerManager = function ()
{
    var Model = new QuizPlayerModel();
    var View = new QuizPlayerView();

    var CurrentQuestion = 0;

    // Quiz Time
    //
    var TotalTime = 0;
    var Hours = 0;
    var Minutes = 0;
    var Seconds = 0;        
    var TimerId = 0;

    return {
        init: function (quizData, startData, endData)
        {
            if(quizData === null || startData === null || endData === null)
            {
                View.renderQuizErrorMessage();
                return;
            }

            Model.setQuizJson(quizData, startData, endData);
            TotalTime = Model.getDuration();

            CurrentQuestion = 0;
            this.calculateTimeAvailable();
            this.createQuizElements();
            this.displayInstructions();
        },
        calculateTimeAvailable: function ()
        {
            // Possibly a function that belongs in the model.

            Hours = Math.floor(TotalTime / 60);
            Minutes = TotalTime - (Hours * 60);           
        },
        incrementQuestion: function ()
        {
            var nextQuestion = CurrentQuestion + 1;

            if(nextQuestion < Model.getNumberOfQuestions())
            {
                CurrentQuestion++;
                return CurrentQuestion;
            }else
            {
                return null;// Reached end of questions
            }
        },
        decrementQuestion: function ()
        {
            var previousQuestion = CurrentQuestion - 1;

            if(previousQuestion >= 0)
            {
                CurrentQuestion--;
                return CurrentQuestion;
            }else
            {
                return null; //Reached start of questions
            }
        },
        displayInstructions: function ()
        {
            var quizStartInstructions = Model.getQuizIntroductionObject();

            // Send copy of QuizManager object so that it is possible to callback
            // to startQuiz from the view.  Bit of a kludge, but I can't find a design
            // pattern that addresses this issue.  Reminder that I'm still quite inexperienced
            // in Javascript!
            View.renderStartMessage(quizStartInstructions, this);
        },
        startTimer: function ()
        {
            TimerId = window.setInterval(this.updateTimeEvent, 1000);
        },
        startQuiz: function ()
        {
            View.hideInfoDialogue();
            View.renderQuestion(CurrentQuestion);
            View.renderProgress(CurrentQuestion, Model.getNumberOfQuestions());
            this.startTimer();
        },
        endQuiz: function()
        {
            var endMessage = Model.getQuizEndObject();

            this.addResponseEvent(); // Record the current selections
            View.renderEndQuizMessage(endMessage, this);
            window.clearInterval(TimerId);            
        },
        createQuizElements:function ()
        {
            var questionList = Model.getQuestionList();
            for(var i = 0; i < questionList.length; i++)
            {
                View.addQuestionTemplate(questionList[i]);
            }
        },
        displayResultEvent: function (resultData)
        {
            View.renderResultMessage(resultData);
        },
        submitToServer: function (controllerReference)
        {
            var userChoices = new UserSelection();

            userChoices.QuizId = Model.getQuizId();
            userChoices.UserId = Model.getUserId();
            userChoices.QuizEventId = Model.getQuizEventId();

            userChoices.Responses = Model.getResponses();

            var userSelections = JSON.stringify(userChoices);

            // Send data asynchronously
            //
            $.ajax({
                type: 'POST',
                url: 'ProcessResult/add',
                data: "response="+userSelections,
                dataType: 'json',
                success: function(data)
                {
                    controllerReference.displayResultEvent(data);
                },
                error: function(data)
                {
                    $('#result_dialogue').append('<h3>Server Error</h3>');
                    $('#result_dialogue').append('<p>'+data.responseText+'</p>').show();
                }
            });             
        },
        addResponseEvent: function ()
        {
            //Get the ids of the user's responses and the current question
            var response = View.getQuestionResponse();
            var questionId = View.getQuestionId();

            //Add the responses to the response object for this question

            if(response !== null)
            {
                Model.addResponse(CurrentQuestion, questionId, response);
                return true;
            }else
            {
                return false;
            }
        },
        nextQuestionEvent: function ()
        {
            //Get the ids of the user's responses and the current question
            /*var response = View.getQuestionResponse();
            var questionId = View.getQuestionId();
            //Add the responses to the response object for this question

            Model.addResponse(CurrentQuestion, questionId, response);
            */
           var nextQuestion = 0;
           var nextQuestionResponse = 0;
           var userResponse = this.addResponseEvent();

            // For the time being users must answer questions before proceeeding
            // If there is already a response for the next question, fetch from the cache
            // and send to the View so the response can be displayed.
            if(userResponse)
            { 
                nextQuestion = this.incrementQuestion();

                if(nextQuestion !== null)
                {
                    View.renderQuestion(nextQuestion);
                    View.renderProgress(CurrentQuestion, Model.getNumberOfQuestions());
                }
                else
                    $.alert('End of Quiz','You have reached the end of the quiz'); // Blocks main thread!  Use jQuery dialogue

                nextQuestionResponse = Model.getResponse(nextQuestion);

                if(nextQuestionResponse !== null)
                {
                   View.setSelectedOptions(nextQuestionResponse);
                }

            }else
            {
                $.alert('No Answer Selected', 'Please select an option before continuing.');//Display jQuery Dialogue asking user for an answer
            }
        },
        previousQuestionEvent: function ()
        {
            var response = View.getQuestionResponse();
            var questionId = View.getQuestionId();
            var previousQuestion = 0;
            var previousQuestionResponse = 0;

            Model.addResponse(CurrentQuestion, questionId, response);

            if(response)
            {
                previousQuestion = this.decrementQuestion();

                if(previousQuestion !== null)
                {
                    View.renderQuestion(CurrentQuestion);
                    View.renderProgress(CurrentQuestion, Model.getNumberOfQuestions());
                }
                else
                    $.alert('Start of Quiz', 'You are already at the start of the Quiz'); // Blocks main thread! Use jQuery dialogue

                previousQuestionResponse = Model.getResponse(previousQuestion);

                if(previousQuestionResponse !== null)
                {
                    View.setSelectedOptions(previousQuestionResponse);
                }

            }else
            {
                $.alert('No Answer Selected', 'Please select an option before continuing.');
            }
        },
        updateTimeEvent: function ()
        {               
            var SecondsRemaining = 0;
            var MinutesRemaining = 0;
            var HoursRemaining = 0;

            if(Seconds < 0)
            {
                Minutes--;
                Seconds = 59;
            }

            if(Minutes === 0 && Hours > 0)
            {
                Minutes = 59;
                Hours--;
            }

            // Coundown complete -- end quiz.
            //
            if(Hours === 0 && Minutes === 0 && Seconds === 0)
            {
                this.endQuiz();
            }

            if(Hours < 10)
            {
                HoursRemaining = '0'+Hours;
            }

            if(Seconds < 10)
            {
                SecondsRemaining = '0'+Seconds;
            }else
                SecondsRemaining = Seconds;

            if(Minutes < 10)
            {
                MinutesRemaining = '0'+Minutes;
            }else
                MinutesRemaining = Minutes;

            View.renderTime(HoursRemaining, MinutesRemaining, SecondsRemaining);

            Seconds--;
        }
    };
};