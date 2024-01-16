package es.dpr.marvelsampleapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.dpr.marvelsampleapp.ui.screens.character.detail.CharacterDetailScreen
import es.dpr.marvelsampleapp.ui.screens.character.list.CharacterListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.CharacterList.route)
    {
        composable(NavItem.Splash) {
            //SplashScreen
        }
        composable(NavItem.CharacterList) {
            CharacterListScreen { characterId ->
                navController.navigate(NavItem.CharacterDetail.createNavRoute(characterId))
            }
        }
        composable(NavItem.CharacterDetail) { backStackEntry ->
            CharacterDetailScreen(
                characterId = backStackEntry.findArg(NavArgs.CharacterId)
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
   composable(
       route = navItem.route,
       arguments = navItem.args,
   ){
       content(it)
   }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArgs): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}