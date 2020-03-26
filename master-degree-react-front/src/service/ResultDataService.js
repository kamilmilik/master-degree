import axios from 'axios'
import {connect} from "react-redux";
import {setIsLoadingFilteredResult, setResult, setSelectedPrice} from "../reducers/actions/actions";
axios.defaults.withCredentials = true;

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/result`;

class ResultDataService {

    retrieveResult() {
        return axios.get(`${API}`, {})
    }

}
export default new ResultDataService()