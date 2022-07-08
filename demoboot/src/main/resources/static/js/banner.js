		var items=document.getElementsByClassName("item");
		 var points=document.getElementsByClassName("yk");
		 var index=0;
		 var time=0;
		 var clearActive=function(){
		 	for(var i=0;i<items.length;i++){
		 		points[i].className="yk";
		 		items[i].className="item";
		 		}
		 	}		 
		 var goIndex=function(){
		 	clearActive();
		 	points[index].className="yk active";
		 	items[index].className="item active";
		 	};
		 	
		 var goNext=function(){
		 	if(index<2){
		 	index++;
		 	}else{
		 		index=0;
		 		}
		 	goIndex();
		 	}
		 var goPre=function(){
		 	if(index==0){
		 	index=2;
		 	}else{
		 		index--;
		 		}
		 	goIndex();
		 	}	
		 for(var i=0;i<points.length;i++){
		 	points[i].addEventListener("click",function(){
		 		var pointsIndex=this.getAttribute("data-index");
		 		index=pointsIndex;
		 		goIndex();
		 		time=0;
		 		})
		 	}	
		 
		 setInterval(function(){
		 	time++;
		 	if(time==20){
		 	goNext();
		 	time=0;
		 	}
		 	},150)