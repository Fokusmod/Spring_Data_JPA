angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8110/market/';

    let page = 1;

    let totalPage;

    $scope.loadProducts = function (index = 1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                i: index
            }
        }).then(function (response) {
            totalPage = response.data.totalPages;
            $scope.productData = response.data
        })
    }

    $scope.loadNext = function () {
        if (totalPage !== page) {
            page = page + 1;
            $scope.loadProducts(page)
        }
    }

    $scope.loadPrevious = function () {
        if (page !== 1) {
            page = page - 1;
            $scope.loadProducts(page)
        }

    }

    $scope.deleteProduct = function (product) {
        $http({
            url: contextPath + 'delete/' + product,
            method: 'GET',
        }).then(function (response) {
            console.log(response)
            $scope.loadProducts();
        })
    }


    $scope.loadProducts(page);


});
