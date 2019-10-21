import { createAction, handleActions } from 'redux-actions'
import Cookies from 'js-cookie'
import api from 'utils/api'

const tokenKey = 'jwt-token'

const LOGIN = 'auth/LOGIN'
const LOGOUT = 'auth/LOGOUT'

export const login = createAction(LOGIN, token => token)
export const logout = createAction(LOGOUT)

const initialState = {
	token: Cookies.get(tokenKey),
}

const auth = handleActions(
	{
		[LOGIN]: (state, { payload: token }) => {
			api.defaults.headers.common['Authorization'] = 'Bearer ' + token
			Cookies.set(tokenKey, token, { expires: 10, path: '/' })
			return { token }
		},
		[LOGOUT]: (state, action) => {
			api.defaults.headers.common['Authorization'] = ''
			Cookies.remove(tokenKey, { path: '/' })
			return { token: '' }
		},
	},
	initialState
)

export default auth

// https://github.com/velopert/learning-react/tree/master/27
