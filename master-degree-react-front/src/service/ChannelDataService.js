import axios from 'axios'

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/channels`;

class ChannelDataService {
    retrieveAllChannels() {
        return axios.get(`${API}`, {
        });
    }
}

export default new ChannelDataService()