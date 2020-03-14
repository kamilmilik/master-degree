import axios from 'axios'

const MAIN_URL = 'http://localhost:8095';
const API = `${MAIN_URL}/api/channels`;
const API_SELECTED = `${MAIN_URL}/api/channels/selected`;
const API_NOT_SELECTED = `${MAIN_URL}/api/channels/not-selected`;
const API_ALL_SELECTED = `${MAIN_URL}/api/channels/all/selected`;
const API_ALL_NOT_SELECTED = `${MAIN_URL}/api/channels/all/not-selected`;


class ChannelDataService {
    retrieveAllChannels() {
        return axios.get(`${API}`, {
        });
    }

    sendSelectedChannel(channel) {
        return axios.post(`${API_SELECTED}`, channel)
            .then(response => response.data);
    }

    sendNotSelectedChannel(channel) {
        return axios.post(`${API_NOT_SELECTED}`, channel)
            .then(response => response.data);
    }

    sendAllSelectedChannels(channels) {
        return axios.post(`${API_ALL_SELECTED}`, channels)
            .then(response => response.data);
    }

    sendAllNotSelectedChannels(channels) {
        return axios.post(`${API_ALL_NOT_SELECTED}`, channels)
            .then(response => response.data);
    }

}

export default new ChannelDataService()