QuestionListManager = function(QListElement)
{
	var QuestionListElement = QListElement;
	
	return {
		/**
		 * Delete all questions from the container element
		 * 
		 */
		clearAllQuestions: function()
		{
			$(QuestionListElement).empty();
		},
		
		getSelectedQuestionsId: function()
		{
			return $('#QuestionList .ui-selected').attr('id');
		},
		
		getQuizDetails: function()
		{
			var quizDetails = [];
			
			quizDetails = $('#quiz_details').serializeArray();
			
			return quizDetails;
		},
                        
                setQuizForEdit: function(Quiz)
                {
                  $('#quiz_title').val(Quiz.getTitle());
                  $('#quiz_level option:contains('+Quiz.getQuizLevel()+')').attr('selected', 'selected');
                  $('#quiz_subject').val(Quiz.getSubject());
                  
                  this.render(Quiz.Questions);
                },
		
		/**
		 * Redraw the List of Questions
		 * 
		 * TODO: Refactor this code to replace with a template
		 * 
                 * 
		 */
		render: function(questionList)
		{
			this.clearAllQuestions();
			
			var html = '';
				
			$.each(questionList, function(key, currentQuestion)
			{
				html+='<li id="'+key+'"class="q">';
				html+='<div class="handle"><span class="ui-icon ui-icon-carat-2-n-s"></span></div>';
				html+='<ul class="question_content">';
				html+='<li><em>Question: </em>'+currentQuestion.QuestionText+'</li>'; // Get the question text
				html+='<li><em>Category: </em> '+currentQuestion.QuestionCategory+'</li>';
				html+='<li><em>Level: </em>'+currentQuestion.QuestionLevel+'</li>';
				html+='<li><em>Options:</em></li>';
				html+='<ul class="options">';
				for(var j = 0; j < currentQuestion.QuestionOptions.length; j++)
				{
					var currentOption = currentQuestion.QuestionOptions[j];
					
					// If the question is correct then display a ticked check box next to it.
					//
					html+='<li>';
					html+=currentOption.OptionText;
					
					if(currentOption.CorrectOption === true)
					{
						html+=' <img width="22px" height="22px" src="images/icons/tick.png"/> ';
					}
					
					html+='</li>';
				}
				html+='</ul>';
				html+='</ul>';
				html+='</li>';
				
			});

			$('#QuestionList').append(html);
			$('#QuestionList').selectable('refresh').find("li.q");
		}
	};
};