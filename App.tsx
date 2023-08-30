import {LogBox} from 'react-native';
import {GameHubNavigation} from "./src/navigation/GameHubNavigation";


// silencing the log messages we don't want to see.
LogBox.ignoreLogs([
    'Non-serializable values were found in the navigation state',
]);

export default function App() {
    return (
        <GameHubNavigation/>
    );
}
