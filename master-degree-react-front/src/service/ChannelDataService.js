import axios from 'axios'
import {CHANNELS_API, SERVER_URL} from "../config/Config";

const API = `${SERVER_URL}${CHANNELS_API}`;

class ChannelDataService {
    retrieveAllChannels() {
        return axios.get(`${API}`, {
        });
    }

}

export default new ChannelDataService()