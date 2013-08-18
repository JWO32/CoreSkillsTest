ReportManager = (function ()
{
    var View = new ReportManagerView();
 
    return {
        init: function ()
        {
            
        },
        downloadGroupsEvent: function ()
        {
            
            $.ajax({
               url: 'Results/getGroup', 
               success: function ()
               {
                   
               },
               error: function ()
               {
                   
               }
            });
        },
        clearCurrentReportEvent: function ()
        {
            
        }
    };
})();

ReportManagerView = (function ()
{
    var ReportCache = [];
    
    return{
        renderGroupList: function (newGroupList)
        {
            
        },
        cacheResults: function (resultList)
        {
            
        }
    };
    
})();