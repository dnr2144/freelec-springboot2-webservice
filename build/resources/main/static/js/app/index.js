/*
* 왜 굳이 main 이라는 변수 안에다가 init이라는 함수를 선언해줬을까?
* var init = function() { ~~~ } <-- 이런 식으로도 충분히 할 수 있었는데
* 그 이유는, 브라우저 스코프는 공용 공간으로 쓰이기 때문에 나중에 로딩된 js의 init, save가 먼저 로딩된
* js의 function을 덮어쓸 수 있기 때문이다.
* 따라서 여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름은 자주 발생할 수 있다.
* 그러다 보니, 이런 문제를 피하려고 index.js 만듸 유효범위(scope)를 만들어서 사용한다.
* 방법은 var index란 객체를 만들어 해당 객체에서 필요한 모든 function을 선언하는 것이다.
* 이렇게 하면 index 객체 안에서만 function이 유효하기 때문에 다른 js와 겹칠 위험이 사라진다.
 */

var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();