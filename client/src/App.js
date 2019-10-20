import React from 'react'
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom'
import { hot } from 'react-hot-loader'
import { StoreProvider } from 'hooks/useStore'
import Home from 'pages/Home'
import Login from 'pages/Login'
import Protected from 'pages/Protected'
import NotFound from 'pages/NotFound'
import PrivateRoute from 'utils/PrivateRoute'

function App() {
	return (
		<StoreProvider>
			<Router>
				<div>
					<Link to="/">HOME</Link>
					<Link to="/login">LOGIN</Link>
					<Link to="/protected">PROTECTED</Link>
				</div>
				<Switch>
					<Route path="/" exact>
						<Home />
					</Route>
					<Route path="/login">
						<Login />
					</Route>
					<PrivateRoute path="/protected">
						<Protected />
					</PrivateRoute>
					<Route>
						<NotFound />
					</Route>
				</Switch>
			</Router>
		</StoreProvider>
	)
}

export default hot(module)(App)
