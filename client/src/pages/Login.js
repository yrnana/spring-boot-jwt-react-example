import React from 'react'
import { useHistory, useLocation } from 'react-router-dom'
import useStore from 'hooks/useStore'

function Login() {
	const {
		token: [, setToken],
	} = useStore()

	let history = useHistory()
	let location = useLocation()

	const handleClick = () => {
		setToken('HELLO')
		history.push(location.state && location.state.from ? location.state.from.pathname : '/')
	}

	return (
		<div>
			<div>Login</div>
			<button onClick={handleClick}>로그인</button>
		</div>
	)
}

export default Login
