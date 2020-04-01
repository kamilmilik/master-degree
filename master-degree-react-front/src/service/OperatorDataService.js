import axios from 'axios'
axios.defaults.withCredentials = true;

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/operators`;

class OperatorDataService {
    retrieveAllOperators() {
        return axios.get(`${API}`, {});
    }
}

export default new OperatorDataService()