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
       // fjern den. hvis du skal logge ind
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
        .then(res => {
          this.setToken(res.token) })
          
    }


    create = (user, pass) => {
      const options = this.makeOptions("POST", true,{ username: user, password: pass });
      return fetch(URL + "/api/create", options, true)
        .then(handleHttpErrors)
        .then(res => {
          this.setToken(res.token) })
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

      submitData = (username, departure, destination, date) => {
        console.log("hej")
        console.log(username, departure, destination, date)
        const options = this.makeOptions("POST", true, {username: username, departure: departure, destination: destination, depTime: date});
        return fetch(URL + "/api/login/data/", options, true)
        .then(handleHttpErrors)
        
        } 
      }
    

    
   
const facade = new ApiFacade();
export default facade;
   
