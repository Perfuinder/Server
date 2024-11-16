package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.Volume;
import com.swu.perfuinder.dto.volume.VolumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VolumeConverter {
    // 단일 Volume 변환 메서드 추가
    public VolumeResponse.VolumeInfo toVolumeInfo(Volume volume) {
        return VolumeResponse.VolumeInfo.builder()
                .volume(volume.getVolume())
                .price(volume.getPrice())
                .build();
    }

    // 리스트 변환 메서드
    public List<VolumeResponse.VolumeInfo> toVolumeResponse(List<Volume> volumes) {
        return volumes.stream()
                .map(this::toVolumeInfo)
                .collect(Collectors.toList());
    }
}