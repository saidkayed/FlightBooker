const URL = "http://localhost:8080/BookerBackend"


function handleHttpErrors(res) {
    if (!res.ok) {
      return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
   }

  export default class userFacade{
    makeOptions(method,addToken,body) {
        var opts = {
          method: method,
          headers: {
            "Content-type": "application/json",
            'Accept': 'application/json',
          }
        }
        if (addToken && this.loggedIn()) {
          opts.headers["x-access-token"] = this.getToken();
        }
        if (body) {
          opts.body = JSON.stringify(body);
        }
        return opts; 
      }

      login = (user, pass) => {
          const options = this.makeOptions("POST", true,{ username: user, password: pass });
          return fetch(URL + "/api/login", options, true)
          .then(handleHttpErrors)
          .then(res => {this.setToken(res.token)})
      }

      setToken = (token) => {
        localStorage.setItem('jwtToken', token)
      }
      getToken = () => {
        return localStorage.getItem('jwtToken')
      }
      loggedIn = () => {
        const loggedIn = this.getToken() != null;
        return loggedIn;
      }
      logout = () => {
        localStorage.removeItem("jwtToken");
      }
      fetchData = () => {
        const options = this.makeOptions("GET",true); //True add's the token
        return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
      }
      fetchAdminData = () => {
        const options = this.makeOptions("GET",true); //True add's the token
        return fetch(URL + "/api/info/admin", options).then(handleHttpErrors);
      }
   }