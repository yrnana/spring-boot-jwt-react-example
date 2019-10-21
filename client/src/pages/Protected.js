import React from 'react'
import { useHistory } from 'react-router-dom'
import { useDispatch } from 'react-redux'
import { logout } from 'stores/auth'
import api from 'utils/api'

function Protected() {
	const dispatch = useDispatch()
	const history = useHistory()

	const handleClick = () => {
		dispatch(logout())
		history.push('/login')
	}

	const fetch = async () => {
		let { data } = await api.get('/employees')
		console.log(data)
	}

	React.useEffect(() => {
		fetch()
	}, [])

	return (
		<div>
			<div>Protected</div>
			<button onClick={handleClick}>로그아웃</button>
		</div>
	)
}

export default Protected
