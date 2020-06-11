import axios from 'axios'
import {RESULT_API, SERVER_URL} from "../config/Config";
axios.defaults.withCredentials = true;

const API = `${SERVER_URL}${RESULT_API}`;

class FilteredResultDataService {

    retrieveFilteredResultByCriteria(criteria) {
        return axios.post(`${API}`, criteria)
            .then(response => {
                return response.data.resultTvPackages.sort((a, b) => (a.filteredTvPackage.price - b.filteredTvPackage.price))
            });
    }

}
export default new FilteredResultDataService()