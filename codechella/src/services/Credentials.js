import axios from 'axios'

const PATH = "http://localhost:8081/api/save"

export default {
    async saveCredentials(cred) {
        let res = await axios.post(PATH, cred)
            .then(response => { return response.status })
            .catch(err => { console.log(err) });  
        return res;
       
    }
}