angular.module('market-front').controller('storeController', function ($scope, $http, $location) {

    const contextPath = 'http://localhost:8110/market/api/v1';

    let page = 1;

    let totalPage;

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

    $scope.addToCart = function (id) {
        $http.post(contextPath + '/cart/' + id)
            .then(function successCallback(response) {
                alert("Товар успешно добавлен.")
            }, function failCallback(response) {
                alert(response.data.message)
            })

    }

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

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function successCallback(response) {
                $scope.loadProducts(page);
            }, function failCallback(response) {
                alert(response.data.message)
            })
    };

    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.loadProducts(page);
});
