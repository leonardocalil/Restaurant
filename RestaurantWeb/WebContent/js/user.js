var app = angular.module("UserApp", ["angular-md5"]); 


app.factory('Auth', function(){
	var user;
	return{
	    setUser : function(aUser){
	    	user = aUser;
	    },
	    getUser: function() {
	    	return user; 
	    },
	    isLoggedIn : function(){	    	
	    	return(user)? user : false;
	    }
	  }
});

app.directive('format', ['$filter', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;


            ctrl.$formatters.unshift(function (a) {
                return $filter(attrs.format)(ctrl.$modelValue)
            });


            ctrl.$parsers.unshift(function (viewValue) {
                              
          elem.priceFormat({
            prefix: '',
            centsSeparator: ',',
            thousandsSeparator: '.'
        });                
                         
                return elem[0].value;
            });
        }
    };
}]);

app.directive("formatPhone", function() {
	return {
	    link : function(scope, element, attrs) {
	        var options = {
	        	onKeyPress: function(val, e, field, options) {
	        		$(element).mask('(00) 00000-0000', options);
	            }
	        }
	        element.bind('blur', function() {
	            adjustMask();
	          });
	 
	        $(element).mask('(00) 00000-0000', options);
	 
	        function adjustMask() {
	            var mask;
	            var cleanVal = element[0].value.replace(/\D/g, '');//pega o valor sem mascara
	            if(cleanVal.length < 11) {//verifica a quantidade de digitos.
	                mask = "(00) 0000-0000";
	                $(element).mask(mask, options);//aplica a mascara novamente
	            }
	            
	        }
	       
	    }
	  }
});


app.directive("formatDocument", function() {
	return {
	    link : function(scope, element, attrs) {
	        var options = {
	        	onKeyPress: function(val, e, field, options) {
	        		$(element).mask('000.000.000/0000-00', options);
	            }
	        }
	        element.bind('blur', function() {
	            adjustMask();
	        });
	 
	        $(element).mask('000.000.000/0000-00', options);
	 
	        function adjustMask() {
	            var mask;
	            var cleanVal = element[0].value.replace(/\D/g, '');//pega o valor sem mascara
	            if(cleanVal.length < 12) {//verifica a quantidade de digitos.
	                mask = "000.000.000-00";
	                $(element).mask(mask, options);//aplica a mascara novamente
	            }
	            
	        }
	       
	    }
	  }
});


app.directive("formatZipCode", function() {
	return {
	    link : function(scope, element, attrs) {
	        var options = {
	        	onKeyPress: function(val, e, field, options) {
	        		//$(element).mask('000.000.000/0000-00', options);
	            }
	        }
	        
	        $(element).mask('00.000-000', options);
	 	       
	    }
	  }
});

app.directive("formatCreditcard", function() {
	return {
	    link : function(scope, element, attrs) {
	        var options = {
	        	onKeyPress: function(val, e, field, options) {
	        		//$(element).mask('000.000.000/0000-00', options);
	            }
	        }
	        
	        $(element).mask('0000 0000 0000 0000', options);
	 	       
	    }
	  }
});

app.directive('validPassword', function() {
	  return {
	    require: 'ngModel',
	    scope: {

	      reference: '=validPassword'

	    },
	    link: function(scope, elm, attrs, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue, $scope) {

	        var noMatch = viewValue != scope.reference
	        ctrl.$setValidity('noMatch', !noMatch);
	        return (noMatch)?noMatch:!noMatch;
	      });

	      scope.$watch("reference", function(value) {;
	        ctrl.$setValidity('noMatch', value === ctrl.$viewValue);

	      });
	    }
	  }
	});

app.controller('HomeCtrl',function ($scope,$http, Auth,md5) {
	
	var page_home = "home.html";
	var page_user = "user.html"; 
	


	$scope.user = {};
	$scope.page = page_home;
	
	$scope.firstname = "";
	
	$scope.logged = Auth.isLoggedIn();
	
	
	if($scope.logged) {
		$scope.user = Auth.isLoggedIn();
		$scope.firstname = $scope.user.split(' ')[0];
	}
	
	
	$scope.menuPage = function(vpage) {
		
		$scope.page = vpage+".html";    	
		
	}

	$scope.newAccount = function() {
		$scope.user = newUser();
		$scope.page = page_user;
		
	}
	$scope.logout = function() {
		Auth.setUser(null);
		$scope.logged = false;
		$scope.firstname = "";
		$scope.page = page_home;
	}
	
	$scope.login = function() {

		$http.post(url_validate_client_user,{user:$scope.user.login,password:md5.createHash($scope.user.password)}).
		then(function(response) {
    		var client = response.data;
    		if(client.id != null) {
    			
    			$scope.user = client;
    					
    			Auth.setUser($scope.user);
    			$scope.logged = Auth.isLoggedIn();
    			$scope.firstname = $scope.user.name.split(' ')[0];
    			$scope.page = page_home;    			
    		} else {    			
    			alert('Usuario e/ou senha invalido!');
    		}
    	});    	
		
		
	}
	
	$scope.save = function() {
	
		$scope.user.password = md5.createHash($scope.user.password);  
		
		$http.get(url_client_exists_login+$scope.user.login)			
		.then(function(response) {
			if(response.data != 0 && response.data != $scope.user.id ) {
				alert('Usuário ('+$scope.user.login+') não pode ser utilizado pois já está sendo usado por outra pessoa');
				
			} else {
				$http.post(url_client_save,$scope.user)			
				.then(function(response) {
					if(response.data > 0) {
						
						$scope.user.id = response.data; 
						Auth.setUser($scope.user);
		    			
		    			$scope.logged = Auth.isLoggedIn();
		    			$scope.firstname = $scope.user.name.split(' ')[0];
		    			$scope.page = page_home;  
					} else {
						alert('Erro ao cadastrar Cliente, desculpe-nos pelo transtorno e, por gentileza, entre em contato com a empresa e informe este problema');
					} 		
					
				});
				
			} 		
			
		});
	}
	
});

