import axios from 'axios'

const COURSE_API_URL = 'http://localhost:8095';
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/api/operators`;

class OperatorDataService {
    retrieveAllOperators() {
        return axios.get(`${INSTRUCTOR_API_URL}`, {
        });
    }
}

export default new OperatorDataService()