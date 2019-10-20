import React from 'react'
import { useHistory } from 'react-router-dom'
import useStore from 'hooks/useStore'
import api from 'utils/api'

function Protected() {
	const {
		token: [, setToken],
	} = useStore()

	let history = useHistory()

	const handleClick = () => {
		setToken('')
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
