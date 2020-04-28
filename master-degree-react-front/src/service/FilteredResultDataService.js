import axios from 'axios'
axios.defaults.withCredentials = true;

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/result`;

class FilteredResultDataService {

    retrieveFilteredResultByCriteria(criteria) {
        return axios.post(`${API}`, criteria)
            .then(response => {
                return response.data.resultTvPackages.sort((a, b) => (a.filteredTvPackage.price - b.filteredTvPackage.price))
            });
    }

}
export default new FilteredResultDataService()