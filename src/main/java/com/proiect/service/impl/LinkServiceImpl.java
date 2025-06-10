package com.proiect.service.impl;

import com.proiect.dto.LinkDto;
import com.proiect.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    @Override
    public LinkDto findLinkById(Long id) {
        return null;
    }

    @Override
    public LinkDto getAllLinks() {
        return null;
    }

    @Override
    public LinkDto createLink(LinkDto link) {
        return null;
    }

    @Override
    public LinkDto updateLink(LinkDto link) {
        return null;
    }

    @Override
    public void deleteLink(Long id) {

    }
}
