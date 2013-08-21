QuizController = function(model, view)
{	
        var QuizModel = model;
        var QuizView = view;
        var SaveTimer = 60000;
        var Timerid = 0;// work timers into save procedure

        return {
                init: function()
                {
                        $.ajaxSetup ({
                            // Disable caching of AJAX responses
                            cache: false
                        });

                        // Load the Question Designer form into designated element.
                        //
                        $('#QuestionDesigner').empty().load('js/templ/new_question.html');
                },

                addQuestionEvent: function(callBack)
                {	
                        questionDialogue(callBack, null, null);
                },

                /**
                 * TODO: Takes the current list of questions -- no longer needed!
                 * @param reorderedList
                 * @returns
                 */
                updateOrderEvent: function(reorderedList)
                {

                },

                validateQuizDetailsEvent: function()
                {
                        var allQuizDetails = [];

                        allQuizDetails = QuizView.getQuizDetails();
                        
                        QuizModel.setTitle(allQuizDetails[0].value);
                        QuizModel.setQuizLevel(allQuizDetails[1].value);
                        QuizModel.setSubject(allQuizDetails[2].value);
                        QuizModel.calcMinutes(allQuizDetails[3].value, allQuizDetails[4].value);
                        
                },

                /**
                 * After the initial save, set a timer so that the quiz is automatically
                 * saved every minute or so.
                 * 
                 * TODO: If the server returns an error, try to save quiz data in local storage
                 * for transmission when link returns.
                 * @returns
                 */

                backgroundSaveEvent: function()
                {
                        $.ajax({

                        });
                },

                /*
                * Remove the selected question from the list of questions and trigger a re-draw.
                */
                deleteQuestionEvent: function()
                {

                        QuizView.render();
                },

                /*
                * Compile the Quiz into XML format and send to the server.
                * 
                * If everything works ok, set a timer to save the updated quiz every 60s
                * 
                * Use $.ajax request in jQuery -- provides more options for handling server response
                */
                saveQuizEvent: function()
                {
                    this.validateQuizDetailsEvent();

                    //var quiz_xml = QuizModel.generateXML();

                    var jsonQuizModel = new Quiz();

                    //Bit of a hack to make the JSON output a bit easier to manipulate on the server
                    //Copy all details into a new Quiz object but ensure that the Questions are an 
                    //array rather than a javascript object
                        
                    // Use jQuery to perform a deep copy of the quiz model object.
                    jsonQuizModel = jQuery.extend(true, {}, QuizModel);
                    
                    jsonQuizModel.Questions = [];
                    
                    $.each(QuizModel.Questions, function(key, Question){
                       
                       arrayIndex = jsonQuizModel.Questions.length;
                       
                       jsonQuizModel.Questions[arrayIndex] = Question;
                        
                    });
                    

                    var quiz_json = jsonQuizModel.generateJSON();

                    $.ajax({
                            type:'POST',
                            url:'Quiz/add/',
                            dataType: 'json',
                            data: "quiz="+quiz_json,
                            cache: false,
                            error: function(data)
                            {
                                    // TODO: Replace with jQuery Dialogue
                                    alert('Server reports an error - unable to save Quiz');
                            },
                            success: function()
                            {
                                    // TODO: Replace with jQuery Dialogue
                                    alert('Server reports Quiz saved');
                                    timerId = (backgroundSaveEvent, SaveTimer);
                            }				
                    });				
                },

                /*
                 * Find out which UI element is currently select and use the ID to locate the 
                 * appropriate question.  Pass the question to question dialogue in edit mode.
                 * 
                 * TODO: Change this so that a jquery dialogue appears from view.
                 * 
                 */
                editQuestionEvent: function(callBack)
                {			
                        if(Object.keys(CurrentQuiz.Questions).length === 0)
                        {
                                alert('No Questions to Edit!');
                                return;
                        }else
                        {
                                var selectedQID = QuizView.getSelectedQuestionsId();
                                var selectedQuestion = CurrentQuiz.Questions[selectedQID];

                                questionDialogue(callBack, true, selectedQuestion);
                        }		
                },

                /*
                *  This is the callback from the dialogue that adds the new question to the question list maintained by the Quiz data model object.
                */
                triggerAddQuestionCallback: function(newQuestion)
                {
                    QuizModel.addQuestion(newQuestion);
                    QuizView.render(CurrentQuiz.Questions);
                },

                triggerEditQuestionCallback: function()
                {

                },

                triggerValidateQuizDetailsCallback: function()
                {

                }
        };
};