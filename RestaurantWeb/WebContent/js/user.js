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

app.run(['$rootScope', '$location', 'Auth', function ($rootScope, $location, Auth) {
    $rootScope.$on('$routeChangeStart', function (event) {

        if (!Auth.isLoggedIn()) {
            //event.preventDefault();
            $location.path('/login')
        }
        else {
            $location.path('/home');
        }
    });
}]);


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
	        
	        $(element).mask('00.000.000-00', options);
	 	       
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

app.controller('UserCtrl', function($scope,$http,$location,md5,Auth) {
	$scope.page = "login.html";
	$scope.user = {};
	$scope.password = "";
	
	
	if(Auth.isLoggedIn()) {
		$scope.page = "user.html";
		$scope.user = Auth.getUser();
	}
	
	$scope.newAccount = function() {
		$scope.user = newUser();
	}
	
	$scope.login = function() {
		
		$http.post(url_validate_user,{user:$scope.user,password:md5.createHash($scope.password)}).
		then(function(response) {
    		var user = response.data;
    		if(user.id != null) {
    			Auth.setUser(user);    	
    			$location.path( "/home" );
    		} else {    			
    			alert('Usuario e/ou senha invalido!');
    		}
    	});    	
		

	}
}); 

app.controller('HomeCtrl',function ($scope,$http, Auth) {
	
	$scope.page = "home.html";
	
	$scope.menuPage = function(vpage) {
		
		$scope.page = vpage+".html";    	
		

	}
	
});


app.controller('ProductTypeCtrl',function ($scope,$http) {
	
	var page_new = "pages/product/product_type_new.html";
	var page_list = "pages/product/product_type_list.html"; 	
	
	$scope.page  = page_list; 
	
	
	$scope.submitted = false;
	
	$scope.models = [];
	
	$http.get(url_product_type_get_all)
	.then(function(response) {
		$scope.models = response.data;		
	});    	
	
	
	$scope.ordenar = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
	
	$scope.new_ = function(vpage) {
		$scope.model = newProductType();				
		
		$scope.page = page_new;    			
	}
	$scope.back = function() {		
		$http.get(url_product_type_get_all)
		.then(function(response) {
			$scope.models = response.data;		
		});    	
		$scope.page  = page_list;    			
	}
	$scope.edit = function(vmodel) {
		
		$scope.model = vmodel;
		$scope.page = page_new;
	}
	$scope.delete_ = function(vmodel) {
		if(confirm("Deseja realmente excluir esse registro ("+vmodel.id+" - "+vmodel.description+")?")) {
			$http.get(url_product_type_delete+vmodel.id).
    		then(function(response) {
    			if(response.data == true) {
    				$scope.back();
    				alert('Registro deletado com sucesso!');
    			} else {
    				alert('Erro ao deletar o registro, por gentileza, entre em contato com seu suporte');
    			}
    		});
		}
	}
	$scope.save = function() {
		
		$scope.submitted = true;
		
		if($scope.model.description != null && $scope.model.description.length > 0) {
			
			
			$http.post(url_product_type_save,$scope.model)
			.then(function(response) {
				if(response.data == true) {
					$scope.submitted = false;
					$scope.back();
				} else {
					alert('Erro ao salvar registro, por gentileza, contate o seu suporte.');
				} 		
				
			});    	
			
		}
		
	}
	
}); 

