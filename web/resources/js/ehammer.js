/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//<![CDATA[ 
$(function(){
    
     $(".mybox").draggable({ containment: "#container" });
        $(".mybox").resizable({containment: "#container"});
        
});

function addImageToConatiner(id, path){
       
        
        var imgURL = path;
        var boxHTML = "<div class='mybox'><img src="+imgURL+" style='width:100%;height:100%'></img></div>"
        
        
        $("#container").append(boxHTML);
        $(".mybox").draggable({ containment: "#container" });
        $(".mybox").resizable({containment: "#container"});
        
        
       
}


function buildHTML(html){
    remoteCommand_BuildAndSaveDesign([{name: 'param', value:html}]);
    event.preventDefault();
}

function printBoxCordinates(){
    
    $( ".mybox" ).each(function( index ) {
        var position = $( this ).position();
        console.log( index + ": left " + position.left + " top :" + position.top );
        event.preventDefault();
    });
    
}

//]]>    