import axios from 'axios'

const COURSE_API_URL = 'http://localhost:8095';
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/rest/channels`;

class ChannelDataService {
    retrieveAllChannels() {
        return axios.get(`${INSTRUCTOR_API_URL}/all`);
    }
}

export default new ChannelDataService()