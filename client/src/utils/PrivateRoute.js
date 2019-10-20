import React from 'react'
import { Route, Redirect } from 'react-router-dom'
import useStore from 'hooks/useStore'

function PrivateRoute({ children, ...rest }) {
	const {
		token: [token],
	} = useStore()

	return (
		<Route
			{...rest}
			render={({ location }) =>
				token ? (
					children
				) : (
					<Redirect
						to={{
							pathname: '/login',
							state: { from: location },
						}}
					/>
				)
			}
		/>
	)
}

export default PrivateRoute
