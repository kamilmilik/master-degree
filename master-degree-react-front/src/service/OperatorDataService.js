import axios from 'axios'
import {OPERATORS_API, SERVER_URL} from "../config/Config";

axios.defaults.withCredentials = true;

const API = `${SERVER_URL}${OPERATORS_API}`;

class OperatorDataService {
    retrieveAllOperators() {
        return axios.get(`${API}`, {});
    }
}

export default new OperatorDataService()