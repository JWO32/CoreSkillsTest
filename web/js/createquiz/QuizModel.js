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
var SCQFLevelDefinition = 
{
	Level: ["SCQF Level 3", "SCQF Level 4", "SCQF Level 5", "SCQF Level 6", "SCQF Level 7", "SCQF Level 8"]
};


//Quiz constructor with all details
Quiz = function(title, duration, subject, level)
{
	this.QuizTitle = title;
	this.QuizDuration =  duration; // Set default duration to 45 minutes
	this.QuizSubject = subject;
        this.TotalMarks = 0;
	this.Questions = {}; //Key-value store of questions
	this.QuizLevel = level; //Quiz level, this might be set by taking an average of the level of the Questions in due course
	this.NumberOfQuestions = 0;
        this.Editing = false;
        
        //Only one result rule in this version, future versions will allow chains of result rules.
        this.QuizRule = '';
};

Quiz.prototype.setEditing= function(editing)
{
    this.Editing = editing;
};

Quiz.prototype.getEditing = function()
{
    return this.Editing;
}

Quiz.prototype.init = function()
{
	
};

Quiz.prototype.calcMinutes = function(hours, minutes)
{
	var numberOfMins = 0;
        
        hours = parseInt(hours);
        minutes = parseInt(minutes);
	
	numberOfMins = (hours * 60) + minutes;
	
        this.setDuration(numberOfMins);
};

Quiz.prototype.addRule = function (rule)
{
    this.QuizRule = rule;
};

Quiz.prototype.getRule = function ()
{
    return this.QuizRule;
};

Quiz.prototype.setQuizLevel = function(level)
{
    this.QuizLevel = level;
};

Quiz.prototype.getQuizLevel = function()
{
    return this.QuizLevel;
};

Quiz.prototype.setTitle = function(title)
{
	this.QuizTitle = title;
};

Quiz.prototype.getTitle = function()
{
    return this.QuizTitle;
};

Quiz.prototype.setDuration =  function(duration)
{
	this.QuizDuration = duration;
};

Quiz.prototype.getDuration = function ()
{
    return this.QuizDuration;
};

Quiz.prototype.setSubject= function(subject)
{
    this.QuizSubject = subject;
};

Quiz.prototype.getSubject = function ()
{
    return this.QuizSubject;
};

Quiz.prototype.getNextUIKey = function(questionId)
{
    if(questionId !== "")
    {
        var q = 'q_'+questionId;
    }else
    {
        var q = 'q_'+Object.keys(this.Questions).length;   
    }
   
    return q;
};

Quiz.prototype.addQuestion = function(newQuestion)
{
    if(typeof newQuestion.QuestionId !== 'undefined')
    {
        var arrayIndex = this.getNextUIKey(newQuestion.QuestionId);
        this.Questions[arrayIndex] = newQuestion;
        this.NumberOfQuestions++;
    }else
    {
        var arrayIndex = this.getNextUIKey(null);
        newQuestion.QuestionId = arrayIndex;
        this.Questions[arrayIndex] = newQuestion;
        this.NumberOfQuestions++;        
    }
    
    
};

Quiz.prototype.getNonUIKey = function ()
{
    return this.QuestionId.replace('q_','');
};

Quiz.prototype.deleteQuestion = function(questionID)
{
	delete this.Questions[questionID];
};

Quiz.prototype.updateQuestion = function(questionID, replacementQuestion)
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
//Quiz.prototype.generateXML = function()
//{
//	var xml = '';
//	
//	xml = '<?xml version="1.0" encoding="ISO-8859-1"?>';
//	xml += '<quiz>';
//	xml +='<quiz-details>';
//	xml += '<title>'+this.Title+'</title>';
//	xml += '<duration>'+this.Duration+'</duration>';
//	xml += '<subject>'+this.Subject+'</subject>';
//	xml += '</quiz-details>';
//	
//	
//	xml += '<question-list number="'+this.NumberOfQuestions+'">';
//	
//	$.each(this.Questions, function(q_id, question)
//	{
//		var currentQuestion = question;
//		
//		xml += '<question>';
//		xml += '<question-text>'+currentQuestion.QuestionText+'</question-text>';
//		xml += '<level>'+currentQuestion.QuestionLevel+'</level>';
//		xml += '<option-list>';
//		
//		for(var i = 0; i < currentQuestion.QuestionOptions.length; i++)
//		{
//			currentOption = currentQuestion.QuestionOptions[i];
//			
//			xml += '<option';
//			
//			if(currentOption.getCorrectOption() === true)
//				xml += ' correct="true">';
//			else
//				xml += '>';
//			
//			xml += currentOption.getOptionText();
//			
//			xml += '</option>';
//		
//		}		
//		xml+='</option-list>';
//		
//		xml += '</question>';
//	});
//	
//	xml+='</question-list>';
//	
//	xml+='</quiz>';
//	
//	return xml;
//};


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
    this.QuestionId = '';
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
    this.OptionId = '';
    this.OptionText = '';
    this.CorrectOption = false;

    return {
            setOptionText: function(newText)
            {
                this.OptionText = newText;
            },
            getOptionText: function()
            {
                return this.OptionText;
            },
            setOptionCorrect: function()
            {
                this.CorrectOption = true;
            },
            setOptionWrong: function()
            {
                this.CorrectOption = false;
            },
            getCorrectOption: function()
            {
                return this.CorrectOption;
            }
    };
};


Rule = function()
{
    this.RuleName = '';
    this.HighMarkBoundary = 0;
    this.LowMarkBoundary = 0;
    this.Category = '';
    this.PassFail = false;
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