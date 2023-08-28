import {StatusBar} from 'expo-status-bar';
import {Text, View} from 'react-native';

export default function App() {
    return (
        <View style={{
            flex: 1,
            backgroundColor: '#fff',
            alignItems: 'center',
            justifyContent: 'center',
        }}>
            <Text>GameHub app main page</Text>
            <StatusBar style="light"/>
        </View>
    );
}
