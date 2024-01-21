export type ApiPlatform = {
    id: number;
    abbreviation: string | null;
    alternative_name?: string;
    category: number;
    created_at: number;
    generation?: number;
    name: string;
    platform_logo: number;
    slug: string;
    updated_at: number;
    url: string;
    platform_family?: number;
    versions: number[];
    websites?: number[];
    checksum: string;
    summary?:string;
};
