import React from 'react'
import { TextField, Button, Box } from '@material-ui/core'
import { useHistory, useLocation } from 'react-router-dom'
import useForm from 'react-hook-form'
import { useDispatch } from 'react-redux'
import { login } from 'stores/auth'
import api from 'utils/api'

function Login() {
	const dispatch = useDispatch()
	const history = useHistory()
	const location = useLocation()

	const { handleSubmit, register } = useForm()
	const onSubmit = async ({ username, password }) => {
		const fd = new FormData()
		fd.append('username', username)
		fd.append('password', password)

		const { data } = await api.post('/user/login', fd)
		dispatch(login(data.token))

		history.push(location.state && location.state.from ? location.state.from.pathname : '/')
	}

	return (
		<Box boxShadow={1} width={300} maxWidth="100%" p={3}>
			<form onSubmit={handleSubmit(onSubmit)}>
				<TextField
					name="username"
					label="아이디"
					fullWidth
					margin="normal"
					inputRef={register({ required: true })}
				/>
				<TextField
					type="password"
					name="password"
					label="비밀번호"
					fullWidth
					margin="normal"
					inputRef={register({ required: true })}
				/>
				<Button type="submit" color="primary" variant="outlined" fullWidth>
					로그인
				</Button>
			</form>
		</Box>
	)
}

export default Login
