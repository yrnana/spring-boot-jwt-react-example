import axios from 'axios'
import Cookies from 'js-cookie'

const tokenKey = 'jwt-token'

export default axios.create({
	baseURL: 'http://localhost:8080/api',
	headers: {
		common: {
			Authorization: Cookies.get(tokenKey) ? 'Bearer ' + Cookies.get(tokenKey) : '',
		},
	},
})
