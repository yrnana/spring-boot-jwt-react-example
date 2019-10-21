import React from 'react'
import { hot } from 'react-hot-loader'
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom'
import Home from 'pages/Home'
import Login from 'pages/Login'
import Protected from 'pages/Protected'
import NotFound from 'pages/NotFound'
import PrivateRoute from 'utils/PrivateRoute'

function App() {
	return (
		<Router>
			<ul>
				<li>
					<Link to="/">HOME</Link>
				</li>
				<li>
					<Link to="/login">LOGIN</Link>
				</li>
				<li>
					<Link to="/protected">PROTECTED</Link>
				</li>
			</ul>
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
	)
}

export default hot(module)(App)
