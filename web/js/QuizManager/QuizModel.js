

/**
 * Quiz Prototype Object
 * 
 * This is the data model used to hold quiz details while the user is creating the quiz using the quiz creation tool.
 * 
 * 06/06/2013
 * Initial implementation will focus on multiple choice questions.
 * 
 */

/**
 * This static sets the available levels for quizzes, this will change to a list of levels downloaded from
 * the server, once the server component has been created.
 */
var SCQFLevelDefinition = function()
{
        $.ajax(
            
        );
	Level: ["SCQF Level 3", "SCQF Level 4", "SCQF Level 5", "SCQF Level 6", "SCQF Level 7", "SCQF Level 8"]	
};


//Quiz constructor with all details
Quiz = function(title, duration, subject, level)
{
	this.Title = title;
	this.Duration =  duration; // Set default duration to 45 minutes
	this.Subject = subject;
	this.Questions = {}; //Key-value store of questions
	this.QuizLevel = level; //Quiz level, this might be set by taking an average of the level of the Questions in due course
	this.NumberOfQuestions = 0;
};

Quiz.prototype.init = function()
{
	
};

Quiz.prototype.calcMinutes = function(hours, minutes)
{
	var numberOfMins = 0;
	
	numberOfMins = (hours * 60) + minutes;
	
	return numberOfMins;
};

Quiz.prototype.setTitle = function(title)
{
	this.Title = title;
};

Quiz.prototype.setDuration =  function(duration)
{
	this.Duration = duration;
};

Quiz.prototype.setSubject= function(subject)
{
	this.Subject = subject;
};

Quiz.prototype.getNextUIKey = function()
{
	var q = 'q_'+Object.keys(this.Questions).length;
	return q;
};

Quiz.prototype.addQuestion = function(newQuestion)
{
	var arrayIndex = this.getNextUIKey();
	this.Questions[arrayIndex] = newQuestion;
	this.NumberOfQuestions++;
};

Quiz.prototype.deleteQuestion = function(questionID)
{
	delete this.Questions[questionID];
};

Quiz.prototype.replaceQuestion = function(questionID, replacementQuestion)
{
	delete this.Questions[questionID];
	
	this.Questions[questionID] = replacementQuestion;
};


Quiz.prototype.generateJSON = function()
{
  var jsonObject = JSON.stringify(this);  
  
  return jsonObject; 
};

/**
 * Very simple XML generator, wrap XML around simple quiz fields
 * Return string containing XML
 * @returns {String}
 */
Quiz.prototype.generateXML = function()
{
	var xml = '';
	
	xml = '<?xml version="1.0" encoding="ISO-8859-1"?>';
	xml += '<quiz>';
	xml +='<quiz-details>';
	xml += '<title>'+this.Title+'</title>';
	xml += '<duration>'+this.Duration+'</duration>';
	xml += '<subject>'+this.Subject+'</subject>';
	xml += '</quiz-details>';
	
	
	xml += '<question-list number="'+this.NumberOfQuestions+'">';
	
	$.each(this.Questions, function(q_id, question)
	{
		var currentQuestion = question;
		
		xml += '<question>';
		xml += '<question-text>'+currentQuestion.QuestionText+'</question-text>';
		xml += '<level>'+currentQuestion.QuestionLevel+'</level>';
		xml += '<option-list>';
		
		for(var i = 0; i < currentQuestion.QuestionOptions.length; i++)
		{
			currentOption = currentQuestion.QuestionOptions[i];
			
			xml += '<option';
			
			if(currentOption.getCorrectOption() === true)
				xml += ' correct="true">';
			else
				xml += '>';
			
			xml += currentOption.getOptionText();
			
			xml += '</option>';
		
		}		
		xml+='</option-list>';
		
		xml += '</question>';
	});
	
	xml+='</question-list>';
	
	xml+='</quiz>';
	
	return xml;
};


/**
 * 
 * Defines a simple question
 * 
 * Properties:
 * 	QuestionText: The actual question asked
 *  QuestionLevel: The SCQF level of the question
 *  QuestionMedia: An array of URLs linking to media selected by the user
 *  QuestionOptions: An array of possible correct answers
 * 
 */

var Question = function()
{
	this.QuestionText = '';
	this.QuestionLevel = '';
	this.QuestionCategory = '';
	this.QuestionScore = 0;
	this.QuestionMedia = [];
	this.QuestionOptions =  [];
	
};

Question.prototype.setQuestionText = function(newText)
{
	this.QuestionText = newText;	
};
	
Question.prototype.getQuestionText = function()
{
	return this.QuestionText;
};

Question.prototype.addOption = function(newOption)
{
		this.QuestionOptions.push(newOption);
};


/**
 * Define an option for a simple multiple choice/multiple selection type question.
 * 
 */
var QuestionOption = function()
{
	var OptionText = '';
	var CorrectOption = false;
	
	return {
		setOptionText: function(newText)
		{
                    OptionText = newText;
		},
		getOptionText: function()
		{
                    return OptionText;
		},
		setOptionCorrect: function()
		{
                    CorrectOption = true;
		},
		setOptionWrong: function()
		{
                    CorrectOption = false;
		},
		getCorrectOption: function()
		{
                    return CorrectOption;
		}
	};
};


/**
 * Defines a MultipleChoice Question
 * 
 * (Currently not implemented)
 */
var MultipleChoiceQuestion = new Question();

MultipleChoiceQuestion = function() 
{
	var Score = 0;
	var QuestionOptions =  {};
	var NumberOfCorrectOptions = 0;
        
        return {
            
        };
};
	
/**
 * 
 * The QuestionFactory is an implementation of the Factory design pattern to create a Question Object in one method call
 * 
 */

var QuestionFactory = function()
{
		
		
};