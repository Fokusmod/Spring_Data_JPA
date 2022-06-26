angular.module('market-front').controller('cartController', function ($scope, $http, $location) {

    const contextPath = 'http://localhost:8110/market/api/v1';


    $scope.loadProductFromCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response){
                $scope.products = response.data;
            })
    }

    $scope.deleteProductFromCart = function (title) {
        $http.delete(contextPath + '/cart/' + title)
            .then(function (response){
                $scope.loadProductFromCart();
            })
    }

    $scope.loadProductFromCart();

});
