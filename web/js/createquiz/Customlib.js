var questionDialogue = function(callBack, edit, question)
{
	/*
	 * Are we using this form to edit a question?
	 */
	edit = (typeof edit === 'undefined') ? false : edit;

	if (edit === true)
	{
		var editQuestion = question;
	} else
	{
		var editQuestion = null; // No question to edit!
	}

	var ValidationTip;

	/**
	 * checkLength Check the length of the given input field.
	 * 
	 * @param inputElement
	 * @param maxLength
	 * @param minLength
	 * @return {Boolean}
	 */
	function checkLength(inputElement, maxLength, minLength)
	{
		if (inputElement.val().length < minLength
		        || inputElement.val().length > maxLength)
		{
			inputElement.addClass('ui-state-error');
			updateTip('Item length must be between ' + minLength + ' and '
			        + maxLength + ' characters.');
			return false;
		} else
		{
			return true;
		}
	};

	/**
	 * 
	 * @param checkboxGroup
	 * @returns {Boolean}
	 */
	function checkAnswerSelected(checkboxGroup)
	{
		if (checkboxGroup.length == 0)
		{
			updateTip('Please select a correct answer to the question.');
			return false;
		} else
			return true;
	};

	/**
	 * Check given input element against a regular expression - this might or
	 * might not be needed.
	 * 
	 * @param inputElement
	 * @param regex
	 */

	function checkRegex(inputElement, regex)
	{

	};

	/**
	 * Update the form validation tip -- tell the user what they have done
	 * wrong.
	 * 
	 * @param newTipText
	 */
	function updateTip(newTipText)
	{
		ValidationTip.text(newTipText).addClass('ui-state-highlight');
		setTimeout(function()
		{
			ValidationTip.removeClass('ui-state-highlight', 1500);
		}, 750);
	};

	$('#QuestionDesigner')
	        .dialog(
                {
                    autoOpen: false,
                    height: 1000,
                    width: 900,
                    modal: true,
                    title: 'Create/Edit Question',
                    show: 'blind',
                    hide: 'blind',
                    open: function()
                    {
	                    // If the editable question is set then add the
	                    // options into the form fields
                    	// If not, reset the form to default values
                    	//
	                    if (editQuestion != null)
	                    {
	                    	$('#question_text').val(editQuestion.QuestionText);
	                    	$('#scqf_level option:contains('+editQuestion.QuestionLevel+')').prop('selected', true);
	                    	$('#category option:contains('+editQuestion.QuestionCategory+')').prop('selected', true);
	                    	$('#mark').val(editQuestion.QuestionScore);
	                    	
	                    	
	                    }else
	                    {
	                    	//Reset all Attributes (the quick way)
	                    	$('#question_details :input, #answer_options :input').val('');	                    	
	                    	$('#answer_options :checked').removeAttr('checked');
	                    	
	                    	//Default score
	                    	$('#question_details :input[type="number"]').val('1');	                    	
	                    }

                    },
                    buttons :
                    {
                "OK": function()
                {
                        var isValid = true;

                        $('#question_details input, #answer_options input').removeClass('ui-state-error');
                        ValidationTip = $('#form_validation_tip');

                        /**
						 * Validation
						 */
                        // The question text entered by the user or
                        // pre-inserted by editing
                        var QuestionTextElement = $('#question_text');
                        var Options = $('input.qoption:text');
                        var CorrectAnswerCheckboxes = $('#answer_options input:checked');

                        // Check Question validity
                        isValid = isValid
                                && checkLength(QuestionTextElement,
                                        255, 1);
                        // Check Option Validity
                        for ( var i = 0; i < Options.length; i++)
                        {
	                        var currOption = $(Options[i]);
	                        isValid = isValid
	                                && checkLength(currOption, 255, 1);
                        }

                        isValid = isValid
                                && checkAnswerSelected(CorrectAnswerCheckboxes);

                        if (isValid)
                        {
	                        var AllQuestionDetails = $(
	                                '#question_details :input')
	                                .serializeArray();
	                        
	                        var AllQuestionOptions = $(
	                                '#answer_options :input')
	                                .serializeArray();
	                        
	                        var QuestionDetails = new Question();
	                        QuestionDetails.QuestionText = AllQuestionDetails[0].value;
	                        QuestionDetails.QuestionLevel = AllQuestionDetails[1].value;
	                        QuestionDetails.QuestionCategory = AllQuestionDetails[2].value;
	                        QuestionDetails.QuestionScore = AllQuestionDetails[3].value;

	                        var Option1 = new QuestionOption();
	                        Option1.OptionText = AllQuestionOptions[0].value;

	                        if ($('#option1correct').is(":checked"))
	                        {
		                        Option1.setOptionCorrect();
	                        }else
                                    Option1.setOptionWrong();

	                        var Option2 = new QuestionOption();

	                        if ($('#option2correct').is(':checked'))
	                        {
		                        Option2.setOptionCorrect();
	                        }else
                                    Option2.setOptionWrong();

	                        Option2.OptionText = AllQuestionOptions[1].value;

	                        var Option3 = new QuestionOption();

	                        if ($('#option3correct').is(':checked'))
	                        {
		                        Option3.setOptionCorrect();
	                        }else
                                    Option3.setOptionWrong();

	                        Option3.OptionText = AllQuestionOptions[2].value;

	                        var Option4 = new QuestionOption();

	                        if ($('#option4correct').is(':checked'))
	                        {
		                        Option4.setOptionCorrect();
	                        }else
                                    Option4.setOptionWrong();

	                        Option4.OptionText = AllQuestionOptions[3].value;

	                        QuestionDetails.addOption(Option1);
	                        QuestionDetails.addOption(Option2);
	                        QuestionDetails.addOption(Option3);
	                        QuestionDetails.addOption(Option4);

	                        // Add the question to the list of questions
	                        // in the model
	                        callBack(QuestionDetails);

	                        $(this).dialog("destroy");
                        }

                        },
                "Cancel": function()
                {
                        $(this).dialog("destroy");
                }
            },
            close: function()
            {
                    $(this).dialog("destroy");
            }
         }).dialog("open");
};