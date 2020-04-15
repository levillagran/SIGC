$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if (text == '_') {


            $.get(href, function (user, status) {
            	$('.myForm #userId').val(user.userId);
                $('.myForm #firstName').val(user.nombre);
               	$('.myForm #lastName').val(user.apellido);
               	$('.myForm #role').val(user.roleId.rol);
				$('.myForm #inputEmail').val(user.correoElectronico);
				$('.myForm #inputAddress').val(user.direccion);
				$('.myForm #inputPhone1').val(user.numeroTelefono1);
				$('.myForm #inputPhone2').val(user.numeroTelefono2);
				$('.myForm #inputUsername').val(user.usuario);
				$('.myForm #inputPassword').val(user.clave);
				$('.myForm #enabled').val(user.enabled);
					
				if(user.enabled){
					$("#enabled").prop('checked', true);
					}
					else
					$("#enabled").prop('checked', false);


            });
            $('.modal-body').load('editAdmin',function(){
		        $('.myForm #exampleModal').modal({show:true});
		    });
           
        } else {

        	$('.modal-body').load(href,function(){
		        $('.myForm #exampleModal').modal({show:true});
		    });
        }

    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delHref').attr('href', href);
        $('#deleteModal').modal();


    });
    
    $('.assign').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href,function(){
	        $('.myFormAuditsRequests #exampleModal').modal({show:true});
	    });
    });
    
    $('.lookCust').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href,function(){
	        $('.myFormCustomer #exampleModal').modal({show:true});
	    });
    });
    

});