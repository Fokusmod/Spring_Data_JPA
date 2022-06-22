angular.module('market-front', []).controller('appController', function ($scope, $http) {

    const contextPath = 'http://localhost:8110/market/api/v1';

    let page = 1;

    let totalPage;


    $scope.loadCategory = function () {
        $http.get(contextPath + '/category')
            .then(function (response) {
                $scope.category = response.data;
            })
    }

    $scope.loadAllProducts = function () {
        $http.get(contextPath + '/all')
            .then(function (response) {
                $scope.products = response.data;
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


    $scope.loadProducts(page);
    $scope.loadAllProducts();
    $scope.loadCategory();


    $scope.createNewProduct = function () {
        let a = document.getElementById('categoryId').value;
        $scope.new_product.categoryTitle = a;
        $http.post(contextPath + '/products', $scope.new_product)
            .then(function successCallback(response) {
                $scope.new_product = null;
                $scope.loadProducts(page);
            }, function failCallback(response) {
                alert(response.data.message)
            })
    };

    $scope.deleteProduct = function (id) {
        $http.delete(contextPath + '/products/' + id)
            .then(function successCallback(response) {
                $scope.loadProducts(page);
            }, function failCallback(response) {
                alert(response.data.message)
            })
    };

    $scope.changeProduct = function () {
        let a = document.getElementById('productId').value;
        $scope.change_product.title = a;
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
