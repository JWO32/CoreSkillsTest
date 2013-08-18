UserManagerView = function($treeWidget, $groupForm, $userForm)
{
    var $TreeWidget = $treeWidget;
    var $GroupDialogue = $groupForm;
    var $UserDialogue = $userForm;
    
    return{
        init: function()
        {
            //Initialise the jsTree
            //
            $TreeWidget.jstree({
           "plugins":["ui", "themes", "html_data"]});
       
        },
        render: function(GroupList)
        {
            
        },
        renderUserGroupList: function(UserandGroupList)
        {
            var finalList = '';
             
            finalList+= '<ul>';
            
            
            for(var i = 0; i < UserandGroupList.length; i++)
            {
                var currentGroup = UserandGroupList[i];
                finalList+= '<li>';
                finalList += '<a>'+currentGroup.GroupName+'</a>';
                
                if(currentGroup.UserList.length > 0)
                {
                    finalList += '<ul>';

                    for(var j=0;j < currentGroup.UserList.length; j++)
                    {
                        var currentUser = currentGroup.UserList[j];
                        finalList+='<li>';
                        finalList+='<a>'+currentUser.FirstName+' '+currentUser.LastName+'</a>';
                        finalList+='</li>';
                    }
                    finalList+='</ul>';
                }
                finalList+='</li>';
            }
            
            finalList+='</ul>';
            
            // Remove the current tree and reset -- probably should think of a better way to do this!
            //
            $TreeWidget.jstree('destroy');
            $TreeWidget.html(finalList);
            $TreeWidget.jstree();
        },
        displayNewGroupDialogue: function(htmlElement, callback, edit, group)
        {
            $GroupDialogue.empty().load('js/templ/new_group.html');
            
            GroupDialogue(htmlElement, callback, edit, 0);
        },
        displayNewUserDialogue: function(htmlElement, callback, edit, user)
        {
            $UserDialogue.empty().load('js/templ/new_user.html');
            
            UserDialogue(htmlElement, callback, edit, 0);
        }
    };
};