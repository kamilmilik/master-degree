import axios from 'axios'
import {connect} from "react-redux";
import {setIsLoadingFilteredResult, setResult, setSelectedPrice} from "../reducers/actions/actions";
import * as qs from "qs";
axios.defaults.withCredentials = true;

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/result`;

class FilteredResultDataService {

    retrieveFilteredResultByCriteria(criteria) {
        return axios.post(`${API}`, criteria)
            .then(response => response.data);
    }

}
export default new FilteredResultDataService()