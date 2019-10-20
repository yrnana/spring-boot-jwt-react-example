import React from 'react'

const StoreContext = React.createContext(null)

export function StoreProvider({ children }) {
	const [token, setToken] = React.useState('')

	const store = {
		token: [token, setToken],
	}

	return <StoreContext.Provider value={store}>{children}</StoreContext.Provider>
}

export default function useStore() {
	const store = React.useContext(StoreContext)
	if (!store) {
		throw new Error('공통 저장소 생성 오류')
	}
	return store
}
