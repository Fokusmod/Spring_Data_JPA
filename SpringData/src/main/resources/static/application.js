angular.module('market-front', []).controller('appController', function ($scope, $http) {

    const contextPath = 'http://localhost:8110/market/api/v1';

    let page = 1;

    let totalPage;


    $scope.createPagesArray = function (start, end) {
        let arr = [];
        for (let i = start; i < end + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.loadProducts = function (index = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                i: index
            }
        }).then(function (response) {
            totalPage = response.data.totalPages;
            $scope.productData = response.data;
            $scope.pagesArray = $scope.createPagesArray(1, totalPage);
            page = index;
        })
    }


    $scope.loadProducts(page);

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                $scope.loadProducts(page);
            }, function failCallback(response) {
                alert(response.message)
            })
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function successCallback(response) {
                $scope.loadProducts(page);
            }, function failCallback(response) {
                alert(response.message)
            })
    };

    $scope.changeProduct = function () {
        $http.put(contextPath + '/products', $scope.change_product)
            .then(function successCallback(response) {
                $scope.change_product = null;
                $scope.loadProducts(page);
            }, function failCallback(response) {
                $scope.change_product = null;
                alert(response.data.message)
            })
    };


    $scope.loadNext = function () {
        if (totalPage !== page) {
            ++page;
            $scope.loadProducts(page)
        }
    }

    $scope.loadPrevious = function () {
        if (page !== 1) {
            --page;
            $scope.loadProducts(page)
        }

    }
});
