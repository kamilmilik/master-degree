import axios from 'axios'
axios.defaults.withCredentials = true;

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/operators`;
const API_SELECTED = `${MAIN_URL}/api/operators/selected`;
const API_NOT_SELECTED = `${MAIN_URL}/api/operators/not-selected`;

class OperatorDataService {
    retrieveAllOperators() {
        return axios.get(`${API}`, {});
    }


    sendSelectedOperator(operator) {
        return axios.post(`${API_SELECTED}`, operator)
            .then(response => response.data);
    }

    sendNotSelectedOperator(operator) {
        return axios.post(`${API_NOT_SELECTED}`, operator)
            .then(response => response.data);
    }
}

export default new OperatorDataService()