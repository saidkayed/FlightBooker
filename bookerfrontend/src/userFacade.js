const URL = "http://localhost:8080/BookerBackend"


function handleHttpErrors(res) {
    if (!res.ok) {
      return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
   }

   class ApiFacade {
    makeOptions(method,addToken,body) {
      var opts = {
        method: method,
        headers: {
          "Content-type": "application/json",
          'Accept': 'application/json',
        }
      }
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
   
const facade = new ApiFacade();
export default facade;
   
