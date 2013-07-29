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
        renderStartMessage: function(quizStartDetails)
        {
            var startTemplateSource = $('#start_quiz_template');
            var template = Handlebars.compile(startTemplateSouce);
            
            var templateHTML = template(quizStartDetails);
        },
        renderEndQuizMessage: function (endMessage, quizRef) 
        {
            
            
        },
        renderResultMessage: function (resultDetails) 
        {
            
        },
        renderTime: function (hours, minutes, seconds) 
        {
            $('#timer').html('<p>' + hours + ':' + minutes + ':' + seconds + '</p>');
        }
    };

};

