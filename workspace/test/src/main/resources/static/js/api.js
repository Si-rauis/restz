
apiService = (function(){
    console.log("api.js");


    function getToken(mid, mpw){
        fetch(url, {       
            method: 'POST',
            headers: { 'Content-Type': 'application/json'  },
            body: JSON.stringify({ "mid" : mid, "mpw" : mpw })
        })                      
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); 
        })
        .then(data => {           
            console.log('Login successful:', data);
         
            saveToken('accessToken', data.accessToken);
            saveToken('refreshToken', data.refreshToken);
            saveToken('mid', mid);
            alert('로그인 성공');
        })
        .catch(error => {          
            console.error('fetch() 실패 :', error);
            alert('로그인 실패 - 다시 시도해주세요.');
        });
    }

       function saveToken(tokenName, tokenValue) {
        let expires = '';
        let days = (24 * 60 * 60 * 1000) * 7;  
        const date = new Date();
        date.setTime(date.getTime() + days);
        expires = '; expires=' + date.toUTCString();

        document.cookie
            = `${tokenName}=${encodeURIComponent(tokenValue)}${expires}`;
    }

    function sendToken(param, callback, error){
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${param.name}=`);

        if (parts.length === 2) {
            const accessToken = parts.pop().split(';').shift();

            $.ajax({
                type: 'get',
                url : param.url,
                headers: { 'Authorization' : 'Bearer ' + accessToken },
                success: function(response){
                    if(callback){
                        callback(response);
                    }
                },
                error: function(xhr, status, err) {
                    if(error){
                        error(err);
                    }
                }
            });
        }
    }

    return { getToken : getToken, saveToken : saveToken, sendToken : sendToken }
})();