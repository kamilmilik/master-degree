import axios from 'axios'
axios.defaults.withCredentials = true;

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/price`;

class PricePickerDataService {
    sendSelectedPrice(data) {
        return axios.post(`${API}`, data);
    }

}

export default new PricePickerDataService()