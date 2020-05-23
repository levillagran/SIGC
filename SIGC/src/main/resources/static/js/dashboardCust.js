$(document).ready(function () {
	
	$('.profile').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargarCust").load(href);
	});
	
	$('.tAudits').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarCust").load(href);
    });
	
	$('.sendEvidenceCust').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
   		$("#seccionRecargarCust").load(href);
    });
	
});