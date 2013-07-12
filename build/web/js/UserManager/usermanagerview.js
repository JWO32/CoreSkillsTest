UserManagerView = function(ListWidget, AddGroupForm, AddUserForm)
{
    var HierarchyListWidget = ListWidget;
    var AddGroupDialogue = AddGroupForm;
    var AddUserDialogue = AddUserForm;
    
    return{
        init: function()
        {
          //AddGroupDialogue.empty().load('js/templ/new_group.html');
          //AddUserDialogue.empty().load('js/templ/new_user.html');
        },
        render: function(GroupList)
        {
            
        },
        renderHierarchicalList: function(UserandGroupList)
        {
            for(var i = 0; i < UserandGroupList.length; i++)
            {


            }
        },
        displayNewGroupDialogue: function(htmlElement, callback, edit, group)
        {
            htmlElement.empty().load('js/templ/new_group.html');
            
            GroupDialogue(htmlElement, callback, edit, 0);
        },
        displayNewUserDialogue: function(htmlElement, callback, edit, user)
        {
            htmlElement.empty().load('js/templ/new_user.html');
            
            UserDialogue(htmlElement, callback, edit, 0);
        }
    };
};