QuizPlayerView = function () 
{
    var QuestionCache = [];

    return {
        renderQuestion: function (index) 
        {
            $('#question').empty();
            if (QuestionCache[index]) {
                $('#question').html(QuestionCache[index]);
            } else {
                $('#question').html('<h3>Error: Cannot display question</h3>');
            }
        },
        addQuestionTemplate: function (newQuestion) 
        {
            var templateSource = $('#question_template').html();
            var template = Handlebars.compile(templateSource);

            var templateHTML = template(newQuestion);

            QuestionCache.push(templateHTML);
        },
        getQuestionTemplate: function (index) 
        {
            return QuestionCache[index];
        },
        getQuestionResponse: function () 
        {
            var responseArray = [];
            
            $('#options input:checked').each(function(key, value)
            {
               responseArray.push(value.id); 
            });
            
            return responseArray;
        },
        getQuestionId: function()
        {
            var QuestionId = $('.display_question').attr('id');
            
            return QuestionId;
        },
        setSelectedOptions: function(responses)
        {
            for(var i = 0; i < responses.SelectedResponse.length; i++)
            {
                $('#'+responses.SelectedResponse[i]).prop('checked', true);
            }
        },
        renderStartMessage: function(quizStartDetails, quizRef)
        {
            var startTemplateSource = $('#start_quiz_template').html();
            var template = Handlebars.compile(startTemplateSource);
            
            var templateHTML = template(quizStartDetails);
                  
            $('#question_wrapper').hide();
            $('#info_dialogue_wrapper').show();
            $('#info_dialogue').html(templateHTML);  
            $('#info_dialogue_button').on('click', function()
            {
                quizRef.startQuiz();
            });
        },
        hideInfoDialogue: function()
        {
            $('#info_dialogue_wrapper').hide();
            $('#question_wrapper').show();
        },
        showQuestion: function()
        {
            $('question_wrapper').show();
        },
        renderEndQuizMessage: function (endMessage, quizRef) 
        {
            var endTemplateSource = $('#end_quiz_template').html();
            var template = Handlebars.compile(endTemplateSource);
            
            var templateHTML = template(endMessage);
            
            
            $('#info_dialogue').html(templateHTML);
            $('#question_wrapper').hide();
            $('#info_dialogue_wrapper').show();
            $('#info_dialogue_button').empty(); // Delete the dialogue button -- user muse close the window to finish     
        },
        renderResultMessage: function (resultDetails) 
        {
            
        },
        renderTime: function (hours, minutes, seconds) 
        {
            $('#timer').html('<p>' + hours + ':' + minutes + ':' + seconds + '</p>');
        },
        renderProgress: function (currentQuestion, totalQuestions)
        {
            $('#progress').html('Question: <strong>'+(currentQuestion+1)+'</strong>/<strong>'+totalQuestions+'</strong>');
        }
    };

};

