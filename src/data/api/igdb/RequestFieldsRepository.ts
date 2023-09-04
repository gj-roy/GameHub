/**
 * As the name suggests, this repository provides all the fields that we need to access in
 * an API call to IGDB HTTP service.
 *
 * todo: to be replaced with apicalypse later on.
 */
export const getAllRequestFields = () => {
    return "fields id,name,category,cover.image_id,first_release_date,game_modes,genres,involved_companies,platforms,player_perspectives,screenshots,summary,videos,websites,themes,url ";
}
